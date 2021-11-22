import React, { useEffect, useState } from "react";
import Tree from "@geist-ui/react/esm/tree";

import { Button, Modal, Alert } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faDoorClosed } from "@fortawesome/free-solid-svg-icons";
import { getDrive, copyFile } from "../api-calls/UserCall";
import { COPY_MODE } from "./AppLayout";

const FileCopyAttendant = ({ global, copyFlag, setCopyFlag }) => {
  const [glob, setGlobal] = global;

  const [path, setPath] = useState(glob.name);
  const [fileToCopy, setFileToCopy] = useState(glob.selectedItem);
  const [dirTarget, setDirTarget] = useState(null);
  const [show, setShow] = useState(true);

  const setFSItem = (item) => {

    if (
      item.type === "directory" &&
      item.id !== glob.selectedItem?.id
    ) {
      setDirTarget(item);
    }
  };

  const handlePath = (p) => setPath(p);

  const handelCopy = () => {

    const username = glob.username;
    const fileId = glob.selectedItem.id;
    const newDirId = dirTarget?.id || null;
    const password = glob.password;

    if (dirTarget == null && glob?.selectedItem !== null) {
        alert('asdasdasd');
    };
    //(username, fileId, virtualDirDestination)
    copyFile(username, fileId, newDirId)
      .then(({ data }) => {
        setCopyFlag(false);
        return getDrive(username, password);
      })
      .then(({ data }) => {
        const updatedDrive = data.object;
        setGlobal({ ...glob, drive: updatedDrive });
        setDirTarget(null);
      });
  };

  const mapFile = (file) => (
    <Tree.File
      id={file.id}
      name={file?.name || "(No name specified)"}
      extra={file.bytesSize}
      onClick={() => setFSItem(file)}
    ></Tree.File>
  );

  const mapDir = (dir) => (
    <Tree.Folder
      id={dir.id}
      name={dir?.name || "(No dir name specified)"}
      onClick={() => setFSItem(dir)}
    >
      {dir.childrenDirectories.map((d) => mapDir(d))}
      {dir.files.map((f) => mapFile(f))}
    </Tree.Folder>
  );

  return (
    <div id="file-mover">
      {copyFlag && glob.drive && (
        <Modal show={show} onHide={() => setShow(false)}>
          <Modal.Header closeButton>
            <Modal.Title>Move files</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {" "}
            <Alert variant="primary">
              {" "}
              Select a dir to copy the file: {glob.selectedItem?.name || "xd"}
            </Alert>
            {dirTarget && <Alert variant="primary">
              {" "}
              Copying to: {dirTarget?.name || "xd"}
            </Alert>}
            <Tree initialExpand={true} onClick={handlePath}>
              {mapDir(glob.drive.rootDir)}{" "}
            </Tree>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={(e) => {e.target.value = null; handelCopy();}} disabled={dirTarget === null}>
              <FontAwesomeIcon icon={faDoorClosed} />
              Copy file
            </Button>
            <Button variant="secondary" onClick={() => {setShow(false); setCopyFlag(false);}}>
              <FontAwesomeIcon icon={faDoorClosed} />
              Close
            </Button>
          </Modal.Footer>
        </Modal>
      )}
    </div>
  );
};

export default FileCopyAttendant;

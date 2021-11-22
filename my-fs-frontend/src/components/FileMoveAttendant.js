import { useState } from "react";
import Tree from "@geist-ui/react/esm/tree";
import { Alert } from "react-bootstrap";

const FileMoveAttendant = ({ global }) => {
  const [glob, setGlobal] = global;

  const [fileToMove, setFileToMove] = useState(glob.selectedItem || null);
  const [dirTarget, setDirTarget] = useState(null);

  const mapFile = (file) => (
    <Tree.File
      id={file.id}
      name={getFileName(file) || "(No name specified)"}
      extra={file.bytesSize}
      onClick={() => setFSItem(file)}
    ></Tree.File>
  );

  const mapDir = (dir) => (
    <Tree.Folder
      id={dir.id}
      name={getFileName(dir)}
      onClick={() => setFSItem(dir)}
    >
      {dir.childrenDirectories.map((d) => mapDir(d))}
      {dir.files.map((f) => mapFile(f))}
    </Tree.Folder>
  );

  return (
    <div id="file-mover">
        <Alert variant="primary"> Select a dir to move the file: {fileToMove.name}</Alert>
        <Tree initialExpand={true} onClick={handlePath}>
            {mapDir(glob.drive.rootDir)}{" "}
        </Tree>
    </div>
  );
};

import React, { useState } from "react";
import {
  Button,
  Container,
  Row,
  Col,
  Modal,
  Form,
} from "react-bootstrap";
import {
  deleteFile,
  downloadFile,
  getDrive,
  newDirectory,
} from "../api-calls/UserCall";
import { FS_MODE } from "../App";

const style = {
  position: "fixed",
  top: 0,
  left: 0,
  backgroundColor: "#3d747e",
  width: "100%",
  height: "6%",
};

const AppBar = ({ global }) => {
  const [glob, setGlobal] = global;

  // modals flags
  const [show, setShow] = useState(false);
  const [showDir, setShowDir] = useState(false);
  const [showViewProperties, setShowViewProperties] = useState(false);

  const [newDirectoryName, setNewDirectoryName] = useState("Nueva Carpeta");

  const [message, setMessage] = useState("");
  const [action, setAction] = useState("");

  const handleDeleteFile = () => {
    const username = glob.username,
      password = glob.password;
    deleteFile(username, glob.selectedItem.id)
      .then(({ data }) => {
        setMessage(
          data.success ? "The file was deleted" : "The file wasn't deleted"
        );
        setAction("Delete file");
        setShow(true);
        return getDrive(username, password);
      })
      .then(({ data }) => {
        alert(JSON.stringify(data));
        setGlobal({
          ...glob,
          driveMode: FS_MODE,
          drive: data.object,
          username,
          password,
        });
      })
      .catch(console.error);
  };

  const handLeDownloadFile = () => {
    const username = glob.username,
      password = glob.password;

    downloadFile(username, glob.selectedItem.id)
      .then(({ data }) => {
        setMessage(
          data.success ? "The file was download" : "The file wasn't download"
        );
        setAction("Download file");
        setShow(true);
        return getDrive(username, password);
      })
      .then(({ data }) => {
        setGlobal({
          ...glob,
          driveMode: FS_MODE,
          drive: data.object,
          username,
          password,
        });
      })
      .catch(console.error);
  };

  const handleNewDirectory = () => {
    const username = glob.username,
      password = glob.password,
      dirId = glob.selectedItem.id;
    newDirectory(username, newDirectoryName, glob.selectedItem.id)
      .then(({ data }) => {
        setMessage(
          data.success
            ? `The directory ${newDirectoryName} was created`
            : `The directory ${newDirectoryName}  wasn\'t created`
        );
        setAction("Create directory");
        setShow(true);
        return getDrive(username, password);
      })
      .then(({ data }) => {
        alert(JSON.stringify(data));
        setGlobal({
          ...glob,
          driveMode: FS_MODE,
          drive: data.object,
          username,
          password,
        });
      })
      .catch(console.error);
  };

  return (
    <div style={style} className="mb-4">
      <Modal show={show} onHide={() => setShow(false)}>
        <Modal.Header closeButton>
          <Modal.Title>{action}</Modal.Title>
        </Modal.Header>
        <Modal.Body>{message}</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShow(false)}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>

      <Modal
        show={showViewProperties}
        onHide={() => setShowViewProperties(false)}
      >
        <Modal.Header closeButton>
          <Modal.Title>Show file properties</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Row>
            <Col><h5>File name:</h5> </Col>
            <Col>{glob.selectedItem?.name}</Col>
          </Row>
          <Row>
            <Col><h5>Extension:</h5> </Col>
            <Col>{glob.selectedItem?.extension}</Col>
          </Row>
          <Row>
            <Col><h5>Size (bytes):</h5> </Col>
            <Col>{glob.selectedItem?.content.length}</Col>
          </Row>
          <Row>
            <Col><h5>Creation date:</h5></Col>
            <Col>{glob.selectedItem?.createdDate}</Col>
          </Row>
          <Row>
            <Col><h5>Modified date:</h5></Col>
            <Col>{glob.selectedItem?.modifiedDate}</Col>
          </Row>
        </Modal.Body>
        <Modal.Footer>
          <Button
            variant="secondary"
            onClick={() => setShowViewProperties(false)}
          >
            Close
          </Button>
        </Modal.Footer>
      </Modal>

      <Modal show={showDir} onHide={() => setShowDir(false)}>
        <Modal.Header closeButton>
          <Modal.Title></Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form.Group as={Col} controlId="formGridEmail">
            <Form.Label>Enter the name of the new directory:</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={newDirectoryName}
              onChange={(e) => setNewDirectoryName(e.target.value)}
            />
          </Form.Group>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="success" onClick={() => handleNewDirectory()}>
            Create
          </Button>
          <Button variant="secondary" onClick={() => setShowDir(false)}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>

      <Container>
        <Row>
          <Col>
            {" "}
            <span className="h1"> U File-System App! </span>
          </Col>
          <Col>
            {" "}
            <div className="d-inline">
              <Button variant="secondary">New File</Button>
              {` `}
              <Button
                variant="secondary"
                disabled={glob.selectedItem?.type != "directory"}
                onClick={() => setShowDir(true)}
              >
                New Directory
              </Button>
              {` `}
              <Button variant="danger" onClick={() => handleDeleteFile}>
                Delete selected
              </Button>
              {` `}
              <Button variant="secondary">Move</Button>
              {` `}
              <Button variant="secondary">Share</Button>
              {` `}
              <Button variant="secondary">Copy</Button>
              {` `}
              <Button variant="secondary" onClick={() => handLeDownloadFile()}>
                Copy Virtual to Real
              </Button>
              {` `}
              <Button
                variant="secondary"
                disabled={glob.selectedItem?.type == "directory"}
                onClick={() => setShowViewProperties(true)}
              >
                Show properties
              </Button>
              {` `}
            </div>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default AppBar;

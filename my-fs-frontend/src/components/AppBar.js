import React, { useState } from "react";
import { Alert, Button, Container, Row, Col, Modal } from "react-bootstrap";
import { deleteFile, downloadFile, getDrive, newDirectory} from "../api-calls/UserCall";
import { FS_MODE } from "../App";

const style = {
  position: "fixed",
  top: 0,
  left: 0,
  backgroundColor: "#3d747e",
  width: "100%",
  height: "6%",
}

const AppBar = ({ global }) => {

  const [glob, setGlobal] = global;
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [newDirectoryName, setNewDirectoryName] = useState('Nueva Carpeta');

  const [message, setMessage] = useState('');
  const [action, setAction] = useState('');

  const handleDeleteFile = () => {
    const username = glob.username, password = glob.password;
    deleteFile(username, glob.selectedItem.id).then(({data}) => {
      setMessage(data.success ? 'The file was deleted' : 'The file wasn\'t deleted');
      setAction('Delete file');
      setShow(true);
      return getDrive(username, password);
    }).then(({data}) => {
      alert(JSON.stringify(data));
      setGlobal({...glob, driveMode : FS_MODE, drive : data.object, username, password})
    }).catch(console.error);
  }

  const handeDownloadFile = () => {
    const username = glob.username, password = glob.password;
    
    downloadFile(username, glob.selectedItem.id).then( ({data}) => {
      setMessage(data.success ? 'The file was download' : 'The file wasn\'t download');
      setAction('Download file');
      setShow(true);
      return getDrive(username, password);
    }).then(({data}) => {
      setGlobal({...glob, driveMode : FS_MODE, drive : data.object, username, password})
    }).catch(console.error);
  }

  const handleNewDirectory = () => {
    const username = glob.username, password = glob.password, dirId = glob.selectedItem.id;
    newDirectory(username, newDirectoryName, glob.selectedItem.id).then(({data}) => {
      setMessage(data.success ? `The directory ${newDirectoryName} was created` : `The directory ${newDirectoryName}  wasn\'t created`);
      setAction('Create directory');
      setShow(true);
      return getDrive(username, password);
    }).then(({data}) => {
      alert(JSON.stringify(data));
      setGlobal({...glob, driveMode : FS_MODE, drive : data.object, username, password})
    }).catch(console.error);
  }

  return (
    <div style={style} className="mb-4">
      
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{action}</Modal.Title>
        </Modal.Header>
        <Modal.Body>{message}</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>

      <Container>
        <Row>
          <Col> <span className="h1"> U File-System App! </span></Col>
          <Col> <div className="d-inline">
            <Button variant="secondary">New File</Button>{` `}
            <Button variant="secondary" disabled={glob.selectedItem.type != "directory"} onClick={()=>handleNewDirectory()}>New Directory</Button>{` `}
            <Button variant="danger" onClick={() => handleDeleteFile}>Delete selected</Button>{` `}
            <Button variant="secondary">Move</Button>{` `}
            <Button variant="secondary">Share</Button>{` `}
            <Button variant="secondary">Copy</Button>{` `}
            <Button variant="secondary" onClick={() => handeDownloadFile()}>Copy Virtual to Real</Button>{` `}
          </div></Col>
        </Row>
      </Container>
    </div>
  );
};

export default AppBar;
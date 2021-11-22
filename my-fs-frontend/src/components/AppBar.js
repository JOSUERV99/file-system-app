import React, { useState,useRef  } from "react";
import { Button, Container, Row, Col, Modal, Form, Alert } from "react-bootstrap";
import {
  deleteFile,
  downloadFile,
  getDrive,
  newDirectory,
  newFile,
  modifyFile,
} from "../api-calls/UserCall";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { toast } from 'bulma-toast'
import {
  faAmericanSignLanguageInterpreting,
  faArchive,
  faArrowAltCircleDown,
  faArrowsAlt,
  faCopy,
  faDoorClosed,
  faFile,
  faFileArchive,
  faScroll,
  faShare,
  faTrash,
} from "@fortawesome/free-solid-svg-icons";
import { FS_MODE } from "../App";
import '../style/AppBar.scss';

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
  const [showNewFile, setShowNewFile] = useState(false);
  const [newFileName, setNewFileName] = useState("New File");

  const [newDirectoryName, setNewDirectoryName] = useState("Nueva Carpeta");

  const [message, setMessage] = useState("");
  const [action, setAction] = useState("");

  const inputFile = useRef(null) 

  const showNotification = (message, type) => {
    toast({
      message: message,
      type: type,
      dismissible: true,
      pauseOnHover: true,
      duration: 2000,
      position: 'bottom-right',
      animate: { in: 'fadeIn', out: 'fadeOut' },
    })
  }

  const handleDeleteFile = () => {
    const username = glob.username,
    password = glob.password;
    deleteFile(username, glob.selectedItem.id)
      .then(({ data }) => {
        data.success ? showNotification("The file was deleted", "is-success") : showNotification("The file wasn't deleted", "is-danger")
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
      .catch(()=>{
        showNotification("There was an error deleting the file, please try again", "is-danger");
      });
  };

  const handLeDownloadFile = () => {
    const username = glob.username,
    password = glob.password;
    downloadFile(username, glob.selectedItem.id)
      .then(({ data }) => {
        data.success ? showNotification("The file was download", "is-success") : showNotification("The file wasn't download", "is-danger")
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
      .catch(()=>{
        showNotification("There was an error downloading the file, please try again", "is-danger")
      });
  };

  const handleUploadFile = (e) => {
    var file = document.querySelector('input[type=file]').files[0];
    var reader = new FileReader()
    var textFile = /text.*/;
    if (file.type.match(textFile)) {
      reader.onload = function (event) {
        let fileNameArray = file.name.split(".");
        fileNameArray.pop();
        let fileName = fileNameArray.join(".");
        let fileContent = event.target.result;
        uploadFile(fileName ,fileContent);
      }
    }else{
      showNotification("It doesn't seem to be a text file", "is-danger")
    }
    reader.readAsText(file);
    e.target.value = null;
  };

  function uploadFile(fileName, fileContent) {
    const username = glob.username,
    password = glob.password;
    newFile(username, glob.selectedItem.id, fileName, fileContent)
      .then(({ data }) => {
        data.success ? showNotification("The file was uploaded", "is-success") : showNotification("The file wasn't uploaded", "is-danger")
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
      .catch(()=>{
        showNotification("There was an error uploading the file, please try again", "is-danger")
      });
  }

  const openFileExplorer = () => {
    document.querySelector('input[type=file]').click();
  };

  const handleNewDirectory = () => {
    const username = glob.username,
    password = glob.password,
    dirId = glob.selectedItem.id;
    newDirectory(username, newDirectoryName, dirId)
      .then(({ data }) => {
        data.success ? showNotification(`The directory ${newDirectoryName} was created`, "is-success") : showNotification(`The directory ${newDirectoryName}  wasn\'t created`, "is-danger")
        
        // setMessage(
        //   data.success
        //     ? `The directory ${newDirectoryName} was created`
        //     : `The directory ${newDirectoryName}  wasn\'t created`
        // );
        // setAction("Create directory");
        // setShow(true);
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
      .catch(()=>{
        showNotification("There was an error creating the directory, please try again", "is-danger")
      });
  };

  const handleNewFile = () => {
    const username = glob.username,
    password = glob.password,
    dirId = glob.selectedItem.id;
    setShowNewFile(false);
    newFile(username, dirId, newFileName, "")
      .then(({ data }) => {
        // setMessage(
        //   data.success
        //     ? `The file  ${newFileName} was created`
        //     : `The directory ${newFileName}  wasn\'t created`
        // );
        // setAction("Create File");
        // setShow(true);
        setShowNewFile(false);
        data.success ? showNotification(`The directory ${newDirectoryName} was created`, "is-success") : showNotification(`The directory ${newDirectoryName}  wasn\'t created`, "is-danger")
		setNewFileName("New File");
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
      .catch(()=>{
        showNotification("There was an error creating the file, please try again", "is-danger")
      });
  };

  const handleSaveFile = () => {
    const username = glob.username,
    password = glob.password,
    dirId = glob.selectedItem.id;
	  modifyFile(username, dirId, glob.selectedItem.content)
      .then(({ data }) => {
        data.success ? showNotification(`The file  ${newFileName} was saved`, "is-success") : showNotification(`The directory ${newFileName}  wasn\'t saved`, "is-danger")
        // setMessage(
        //   data.success
        //     ? `The file  ${newFileName} was updated`
        //     : `The directory ${newFileName}  wasn\'t updated`
        // );
        // setAction("MOdify File");
        // setShow(true);
        setNewFileName("New File")
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
      .catch(()=>{
        showNotification("There was an error saving the file, please try again", "is-danger")
      });

  };

  return (
    <div style={style} className="mb-4">
      <input type="file" ref={inputFile} style={{display: 'none'}} onChange={(e) => handleUploadFile(e)} />
      <Modal show={show} onHide={() => setShow(false)}>
        <Modal.Header closeButton>
          <Modal.Title>{action}</Modal.Title>
        </Modal.Header>
        <Modal.Body>{message}</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShow(false)}>
            <FontAwesomeIcon icon={faDoorClosed} />
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
            <Col>
              <h5>File name:</h5>{" "}
            </Col>
            <Col>{glob.selectedItem?.name}</Col>
          </Row>
          <Row>
            <Col>
              <h5>Extension:</h5>{" "}
            </Col>
            <Col>{glob.selectedItem?.extension}</Col>
          </Row>
          <Row>
            <Col>
              <h5>Size (bytes):</h5>{" "}
            </Col>
            <Col>{glob.selectedItem?.content?.length}</Col>
          </Row>
          <Row>
            <Col>
              <h5>Creation date:</h5>
            </Col>
            <Col>{glob.selectedItem?.createdDate}</Col>
          </Row>
          <Row>
            <Col>
              <h5>Modified date:</h5>
            </Col>
            <Col>{glob.selectedItem?.modifiedDate}</Col>
          </Row>
        </Modal.Body>
        <Modal.Footer>
          <Button
            variant="secondary"
            onClick={() => setShowViewProperties(false)}
          >
            <FontAwesomeIcon icon={faDoorClosed} />
            {` `}
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

	    <Modal show={showNewFile} onHide={() => setShowNewFile(false)}>
        <Modal.Header closeButton>
          <Modal.Title></Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form.Group as={Col} controlId="formGridEmail">
            <Form.Label>Enter the name of the new file:</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={newFileName}
              onChange={(e) => setNewFileName(e.target.value)}
            />
          </Form.Group>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="success" onClick={() => handleNewFile()}>
            Create
          </Button>
          <Button variant="secondary" onClick={() => setShowNewFile(false)}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>

      <Container>
        <Row>
          <Col md={5} lg={5}>
            {" "}
            <span className="h2"><FontAwesomeIcon icon={faFileArchive} size={"lg"} spin={true}/>{`     `}File-System App!</span>
          </Col>
        </Row>
        {/* <Row className="mt-2">
          <Col>
            {" "}
            <div className="">
              <Button variant="success" 
			                disabled={glob.selectedItem?.type != "directory"}
			                onClick={() => setShowNewFile(true)}
			        >
                <FontAwesomeIcon icon={faFile} />
                {` `}
                New File
              </Button>
              {` `}
              <Button
                variant="primary"
                disabled={glob.selectedItem?.type != "directory"}
                onClick={() => setShowDir(true)}
              >
                <FontAwesomeIcon icon={faArchive} />
                {` `}
                New Directory
              </Button>
              {` `}
              <Button variant="danger" onClick={() => handleDeleteFile()}>
                <FontAwesomeIcon icon={faTrash} />
                {` `}
                Delete selected
              </Button>
              {` `}
              <Button variant="warning">
                <FontAwesomeIcon icon={faArrowsAlt} />
                {` `}
                Move
              </Button>
              {` `}
              <Button variant="warning">
                <FontAwesomeIcon icon={faShare} />
                {` `}
                Share
              </Button>
              {` `}
              <Button variant="warning">
                <FontAwesomeIcon icon={faCopy} />
                {` `}
                Copy
              </Button>
              {` `}
              <Button variant="warning" onClick={() => handLeDownloadFile()}>
                <FontAwesomeIcon icon={faAmericanSignLanguageInterpreting} />
                {` `}
                Copy Virtual to Real
              </Button>
              {` `}
              <Button variant="warning" onClick={() => openFileExplorer()}>
                <FontAwesomeIcon icon={faAmericanSignLanguageInterpreting} />
                {` `}
                Copy Real to Virtual
              </Button>
              {` `}
              <Button
                variant="warning"
                disabled={glob.selectedItem?.type == "directory"}
                onClick={() => setShowViewProperties(true)}
              >
                <FontAwesomeIcon icon={faScroll}/>
                {` `}
                Show properties
              </Button>
			  <Button
                variant="warning"
                disabled={!(glob.selectedItem?.type != "directory" && glob.selectedItem?.change)}
                onClick={() => handleSaveFile()}
              >
                <FontAwesomeIcon icon={faScroll}/>
                {` `}
                Save File
              </Button>
              {` `}
            </div>
          </Col>
        </Row> */}
      </Container>
    </div>
  );
};

export default AppBar;

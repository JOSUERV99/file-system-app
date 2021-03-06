import { Form, Button, Modal } from "react-bootstrap"
import { useState } from "react";
import { getDrive } from "../api-calls/UserCall";

import "../style/Login.css";

import { FS_MODE, SIGNIN_MODE, SIGNUP_MODE } from "../App";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faDoorOpen } from "@fortawesome/free-solid-svg-icons";

export const Login = ({global}) => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [glob, setGlobal] = global;

    const handleSubmit = () => {
        getDrive(username, password).then(({data}) => {
            localStorage.setItem("username", username);
            setGlobal({...glob, driveMode : FS_MODE, drive : data.object, username, password})
        }).catch(error => {
            alert(error);
        })
    }
    
    const handleCreateUser = () => {
        setGlobal({...glob, driveMode : SIGNUP_MODE})
    }

    return (
        <div className="Login">
            <Modal show={true} fullscreen={true}>
                <Modal.Header closeButton>
                    <Modal.Title>Inicio de sesion de usuario</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form className="p-4">
                        <Form.Group size="lg" controlId="username">
                            <Form.Label>Username</Form.Label>
                            <Form.Control
                                autoFocus
                                type="username"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                            />
                        </Form.Group>
                        <Form.Group size="lg" controlId="password" className="mt-4">
                            <Form.Label>Password</Form.Label>
                            <Form.Control
                                type="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            />
                        </Form.Group>
                        <Button block size="lg" type="" className="mt-4 bg-info btn btn-outline" onClick={() => handleSubmit()}>
                            <FontAwesomeIcon icon={faDoorOpen} />
                            Go to my FS!
                        </Button>
                        <Button block size="lg" type="" className="mt-4 bg-info btn btn-outline" onClick={() => handleCreateUser()}>
                            <FontAwesomeIcon icon={faDoorOpen} />
                            Create User
                        </Button>
                    </Form>
                </Modal.Body>
            </Modal>
        </div>

    );
}
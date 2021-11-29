import { Form, Button, Modal } from "react-bootstrap"
import { useState } from "react";
import { getDrive,createUser } from "../api-calls/UserCall";

import "../style/Login.css";

import { FS_MODE, SIGNIN_MODE, SIGNUP_MODE } from "../App";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faDoorOpen } from "@fortawesome/free-solid-svg-icons";

export const SignIn = ({global}) => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [cpassword, setCPassword] = useState("");

    const [glob, setGlobal] = global;

    const handleCreateUser = () => {
        if(password != cpassword || password == "" || cpassword == ""){
            alert("Password is not the same!");
        } else if(username == ""){
            alert("Password cannot be empty")
        } else if(username != "" && password == cpassword){
            createUser(username,password).then(({data}) => {
                localStorage.setItem("username", username);
                setGlobal({...glob, driveMode : FS_MODE, drive : data.object, username, password})
            }).catch(error => {
                alert(error);
            })
        }
    }

    return (
        <div className="SignIn">
            <Modal show={true} fullscreen={true}>
                <Modal.Header closeButton>
                    <Modal.Title>Creacion de usuario</Modal.Title>
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
                        <Form.Group size="lg" controlId="cpassword" className="mt-4">
                            <Form.Label>Confirm Password</Form.Label>
                            <Form.Control
                                type="password"
                                value={cpassword}
                                onChange={(e) => setCPassword(e.target.value)}
                            />
                        </Form.Group>
                        <Button block size="lg" type="" className="mt-4 bg-info btn btn-outline" onClick={() => handleCreateUser()}>
                            <FontAwesomeIcon icon={faDoorOpen} />
                            Create User!
                        </Button>
                    </Form>
                </Modal.Body>
            </Modal>
        </div>

    );
}
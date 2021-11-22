import React from "react";
import AppBar from "./AppBar";
import FileContentViewer from "./FileContentViewer";
import FileSystemViewer from "./FileSystemViewer";
import { Container, Row, Col,Modal } from 'react-bootstrap';
import { useState, useEffect } from "react";
import { Login } from "./Login";
import { SignIn } from "./SignIn";

import { FS_MODE, SIGNIN_MODE, SIGNUP_MODE } from "../App";

const AppLayout = ({global}) => {

    const [glob, setGlobal]  = global;

    return (
        <div id="container">
            {
                glob.driveMode==FS_MODE ?
                <Container style={{background : "red", width: "100%", display: "contents"}}>
                    <Row lg={2} md={2} className="m-4">
                        <Col>
                            <AppBar global={[glob, setGlobal]}/>
                        </Col>
                    </Row>
                    <Row lg={3} md={3} className="m-4">
                        <Col> 
                            <FileSystemViewer global={[glob, setGlobal]}/>
                        </Col>
                        <Col>
                            <FileContentViewer global={[glob, setGlobal]}/>
                        </Col>
                    </Row>
                </Container>

                : glob.driveMode==SIGNIN_MODE ?

                <Login global={[glob, setGlobal]}/>

                : glob.driveMode==SIGNUP_MODE ?
 
                <SignIn global={[glob, setGlobal]} />
                :
                <></>
            }
        </div>
        );
};

export default AppLayout;
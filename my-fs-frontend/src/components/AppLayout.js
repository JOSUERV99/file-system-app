import React from "react";
import AppBar from "./AppBar";
import ButtonsContainer from "./ButtonsContainer";
import FileContentViewer from "./FileContentViewer";
import FileSystemViewer from "./FileSystemViewer";
import FileMoveAttendant from "./FileMoveAttendant";
import FileCopyAttendant from "./FileCopyAttendant";
import { Container, Row, Col } from 'react-bootstrap';
import { useState } from "react";
import { Login } from "./Login";
import { SignIn } from "./SignIn";

import { FS_MODE, SIGNIN_MODE, SIGNUP_MODE } from "../App";

export const STANDARD_MODE = 0, MOVE_MODE = 1, COPY_MODE = 2;

const AppLayout = ({global}) => {

    const [glob, setGlobal]  = global;
    
    const [moveFlag, setMoveFlag] = useState(false);

    const [copyFlag, setCopyFlag] = useState(false);

    return (
        <div id="container">
            <FileMoveAttendant global={[glob, setGlobal]} moveFlag={moveFlag} setMoveFlag={setMoveFlag}/>
            <FileCopyAttendant global={[glob, setGlobal]} copyFlag={copyFlag} setCopyFlag={setCopyFlag}/>
            {
                glob.driveMode==FS_MODE ?
                <Container style={{background : "red", width: "100%", display: "contents"}}>
                    
                    <Row lg={2} md={2} className="m-4">
                        <Col>
                            <AppBar global={[glob, setGlobal]}/>
                        </Col>
                    </Row>
                    <Row className="m-1">
                        <Col md={3}> 
                            <FileSystemViewer global={[glob, setGlobal]}/>
                        </Col>
                        <Col>
                            <FileContentViewer global={[glob, setGlobal]}/>
                        </Col>
                        <Col md={2}>
                            <ButtonsContainer global={[glob, setGlobal]} setMoveFlag={setMoveFlag} setCopyFlag={setCopyFlag}/>
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
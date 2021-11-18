import React from "react";
import AppBar from "./AppBar";
import FileContentViewer from "./FileContentViewer";
import FileSystemViewer from "./FileSystemViewer";
import { Container, Row, Col, Stack } from 'react-bootstrap';

const AppLayout = (props) => {

    return (

        <Stack direction="horizontal" gap={1}>
            <div className="bg-light border"><AppBar /></div>
            <div className="bg-light border p-auto my-5"><FileSystemViewer /></div>
            <div className="bg-light border"></div>
        </Stack>



    //     <Container>
    //         <Row lg={10} md={10}>
    //             <Col lg={10} md={10} style={{background: "red", width: "100%"}} >
                  
    //             </Col>
    //         </Row>
    //         <Row>
    //             <Col lg={5} md={5} >
    //                 <FileContentViewer/>
    //             </Col>
    //             <Col lg={5} md={5}>
    //                 <FileSystemViewer />
    //             </Col>
    //         </Row>
    //   </Container>
        );
};

export default AppLayout;
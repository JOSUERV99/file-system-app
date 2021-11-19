import React from "react";
import AppBar from "./AppBar";
import FileContentViewer from "./FileContentViewer";
import FileSystemViewer from "./FileSystemViewer";
import { Container, Row, Col, Stack } from 'react-bootstrap';

const AppLayout = (props) => {

    return (

        // <Stack direction="horizontal" gap={1}>
        //     <div className="bg-light border"><AppBar /></div>
        //     <div className="bg-light border p-auto my-5"><FileSystemViewer /></div>
        //     <div className="bg-light border"></div>
        // </Stack>



        <Container >
            <Row lg={3} md={3}>
                <Col />
                <Col  style={{background: "green", width:"50%", padding: 5}} >
                    Un tipo de buscador<Col />
                </Col>

            </Row>
            <Row lg={3} md={3}>
                <Col> 
                    <FileSystemViewer />
                    
                </Col>
                <Col >
                    <FileContentViewer/>
                </Col>
            </Row>
      </Container>

        );
};

export default AppLayout;
import React, { useState, useEffect } from "react";
import { Form } from "react-bootstrap";
import { EDIT_MODE, EXPLORER_MODE } from "../App";

const style = {
    paddingTop: "1%"
};

const FileContentViewer = ({global}) => {

    const [glob, setGlobal] = global;
    const [itemType, setItemType] = useState(glob?.selectedItem?.type || null);
    const [selectedItemContent, setSelectedItemContent] = useState(glob?.selectedItem?.content || null);

    return (
        <div style={style}>
            
            {
                glob.fileMode == EDIT_MODE &&
                <div>
                    <div className="mt-4"></div>
                    <br />
                    <Form>
                        <Form.Group className="mb-3" controlId="fileEditor">
                            <Form.Label>Edit file content</Form.Label>
                            <Form.Control as="textarea" rows={20} size="lg" value={glob.selectedItem?.content || "NO CONTENT"}/>
                        </Form.Group>
                    </Form>
                </div>
            }
            {
                glob.fileMode == EXPLORER_MODE &&
                <div >
                   <h1>SALUDOS</h1>
                </div>
            }
        </div>
        
    );
};

export default FileContentViewer;
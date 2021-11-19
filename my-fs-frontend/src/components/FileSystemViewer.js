import React,  {useState, useEffect} from "react";
import Tree from "@geist-ui/react/esm/tree";
import { Alert } from "react-bootstrap";

import dummyDrive from './dummyDrive.json';

const color = {
    background: "#3d747e",
}

const FileSystemViewer = ({global}) => {

    const [glob, setGlobal] = global;
    const [path, setPath] = useState(glob.name);

    const setFSItem = (item) => { 
        setGlobal({...glob, selectedItem : item }); 
    }

    const handlePath = (p) => {
        alert('asdasd')
        setPath(p);
    }

    const mapFile = (file) => <Tree.File id={file.id} name={file.name || '(No name specified)'} extra={file.bytesSize} onClick={() => setFSItem(file)}></Tree.File>;
    const mapDir = (dir) => (
        <Tree.Folder id={dir.id} name={dir.name } onClick={() => setFSItem(dir)}>
            {dir.childrenDirectories.map(d => mapDir(d))}
            {dir.files.map(f => mapFile(f))}
        </Tree.Folder>
    )

    return (
        <div style={{position: "fixed", x:0, y:0, height: "100%", zoom: "125%"}} className="mt-4 border border" >
            {
                glob.selectedItem &&
                <Alert variant='info'>{glob.selectedItem.name  || '(No name specified)'}</Alert>
            }
            
            <Tree initialExpand={true} onClick={handlePath}>{mapDir(glob.drive.rootDir)} </Tree>
            
        </div>
    );
};

export default FileSystemViewer;
import React,  {useState, useEffect} from "react";
import Tree from "@geist-ui/react/esm/tree";
import { Alert } from "react-bootstrap";

import dummyDrive from './dummyDrive.json';

const FileSystemViewer = (props) => {
    const [selectedFSItemId, setSelectedFSItemId] = useState('');
    const [selectedItem, setSelectedItem] = useState({});
    const [drive, setDrive] = useState(dummyDrive.object);

    const setFSItem = (item) => { setSelectedFSItemId(item.id); setSelectedItem(item);}

    const mapFile = (file) => <Tree.File id={file.id} name={file.name} extra={file.bytesSize} onClick={() => setFSItem(file)}></Tree.File>;
    const mapDir = (dir) => (
        <Tree.Folder id={dir.id} name={dir.name} onClick={() => setFSItem(dir)}>
            {dir.childrenDirectories.map(d => mapDir(d))}
            {dir.files.map(f => mapFile(f))}
        </Tree.Folder>
    )

    return (<>
        <Tree initialExpand={true}>{mapDir(drive.rootDir)}</Tree>
        
        <Alert variant='primary'>{selectedItem && `${selectedItem.name} ${selectedItem.id}`}</Alert>
        </>
    );
};

export default FileSystemViewer;
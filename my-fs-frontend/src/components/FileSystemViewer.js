import React, { useState, useEffect } from "react";
import Tree from "@geist-ui/react/esm/tree";
import { Alert, Tabs, Tab } from "react-bootstrap";
import { faHome } from "@fortawesome/free-solid-svg-icons";

const color = {
    background: "#3d747e",
};

const OWN_MODE = 0,
    SHARE_MODE = 1;

const FileSystemViewer = ({ global }) => {
    const [glob, setGlobal] = global;
    const [path, setPath] = useState(glob.name);

    const [mode, setMode] = useState(OWN_MODE);
    const [selectedTab, setSelectedTab] = useState(mode == OWN_MODE ? "rootDir" : "sharedWithMeDir");

    useEffect(() => {
        setGlobal({ ...glob, selectedItem: mode == OWN_MODE ? glob.drive.rootDir : glob.drive.sharedWithMeDir });
        return () => { };
    }, []);

    const setFSItem = (item) => {
        setGlobal({ ...glob, selectedItem: item });
    };

    const handlePath = (p) => setPath(p);
    const handleDrag = (e) => {
        alert(e);
    };

    const mapFile = (file) => (
        <Tree.File
            id={file.id}
            name={file.name || "(No name specified)"}
            extra={file.bytesSize}
            onClick={() => setFSItem(file)}
        ></Tree.File>
    );
    const mapDir = (dir) => (
        <Tree.Folder id={dir.id} name={dir.name} onClick={() => setFSItem(dir)}>
            {dir.childrenDirectories.map((d) => mapDir(d))}
            {dir.files.map((f) => mapFile(f))}
        </Tree.Folder>
    );

    return (
        <div
            style={{position: "fixed", x: 0, y: 0, height: "90%", width : "23%",zoom: "110%",overflowY: "scroll", overflowX: "scroll"  }}
            className=""
        >
            <div className=""></div>

            <Tabs
                id="controlled-tab-example"
                activeKey={selectedTab}
                onSelect={(k) => setSelectedTab(k)}
                className="mb-3"
            >
                <Tab eventKey="rootDir" title="Home" >
                    {glob.selectedItem && (
                        <Alert variant="info">
                            {glob.selectedItem.name || "(No name specified)"}
                        </Alert>
                    )}
                    <Tree style = {{display: "inline-block"}} initialExpand={true} onClick={handlePath}>
                        {mapDir(
                            glob.drive.rootDir
                        )}{" "}
                    </Tree>
                </Tab>
                <Tab eventKey="sharedWithMe" title="Shared With Me">
                {glob.selectedItem && (
                        <Alert variant="info">
                            {glob.selectedItem.name || "(No name specified)"}
                        </Alert>
                    )}
                    <Tree initialExpand={true} onClick={handlePath}>
                        {mapDir(
                            glob.drive.sharedWithMeDir
                        )}{" "}
                    </Tree>
                </Tab>
            </Tabs>

        </div>
    );
};

export default FileSystemViewer;

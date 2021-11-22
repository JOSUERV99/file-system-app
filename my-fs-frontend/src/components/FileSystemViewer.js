import React, { useState, useEffect } from "react";
import Tree from "@geist-ui/react/esm/tree";
import { Alert, Tabs, Tab } from "react-bootstrap";
import { faHome } from "@fortawesome/free-solid-svg-icons";
import {getSharedFiles, getDrive} from "../api-calls/UserCall";
import { toast } from 'bulma-toast'
import { FS_MODE } from "../App";
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

    const showNotification = (message, type) => {
        toast({
            message: message,
            type: type,
            dismissible: true,
            pauseOnHover: true,
            duration: 2000,
            position: 'bottom-right',
            animate: { in: 'fadeIn', out: 'fadeOut' },
        })
    }

    const handleTabSelection = (k) => {
        glob.selectedItem = null //NO SE SI ESTO ESTE BIEN
        setSelectedTab(k);
        if(k == "sharedWithMe"){
            const username = glob.username
            const password = glob.password;
            getSharedFiles(username)
            .then(({ data }) => {
                // setGlobal({...glob, drive : {...glob.drive, shareWithMeDir: data.object}})
                // data.success ? showNotification("Shared files updated", "is-success") : showNotification("Shared files not updated", "is-danger")
                return getDrive(username, password);
            }).then(({ data }) => {
                setGlobal({
                    ...glob,
                    driveMode: FS_MODE,
                    drive: data.object,
                    username,
                    password,
                });
            })
            .catch((e) => {
                console.log(e)
                showNotification("There was an error updating the shared files, please try again", "is-danger");
            });
        }
        setMode(k == "rootDir" ? OWN_MODE : SHARE_MODE)
        setGlobal({ ...glob, selectedItem: mode == OWN_MODE ? glob.drive.rootDir : glob.drive.sharedWithMeDir });
    }

    const handlePath = (p) => setPath(p);
    const handleDrag = (e) => {
        alert(e);
    };

    const getUsernameRefByFileId = (fileId) => fileId;

    const getFileName = (dir) => {
        if (mode === OWN_MODE) return dir.name;
        console.log(dir);
        return (dir.name !== "shareWithMeDir") ? dir.name : `${dir.name} by ${getUsernameRefByFileId(dir.id)}`
    }

    const mapFile = (file) => (
        <Tree.File
            id={file.id}
            name={getFileName(file) || "(No name specified)"}
            extra={file.bytesSize}
            onClick={() => setFSItem(file)}
        ></Tree.File>
    );
    const mapDir = (dir) => (
        <Tree.Folder id={dir.id} name={getFileName(dir)} onClick={() => setFSItem(dir)}>
            {dir.childrenDirectories.map((d) => mapDir(d))}
            {dir.files.map((f) => mapFile(f))}
        </Tree.Folder>
    );

    return (
        <div
            style={{position: "fixed", x: 0, y: 0, height: "90%", width : "23%",zoom: "110%",overflowY: "scroll", overflowX: "scroll"  }}
            className=""
        >
            <div className="mt-4"></div>

            <Tabs
                id="controlled-tab-example"
                activeKey={selectedTab}
                onSelect={(k) => handleTabSelection(k)}
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
                    <Tree style = {{display: "inline-block"}} initialExpand={true} onClick={handlePath}>
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

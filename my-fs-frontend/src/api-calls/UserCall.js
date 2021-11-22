import axios from "axios";

import env from "../environment.json";

export const getUrl = () => env.dev.baseUrl;
export const getDrive = (username, password) => axios.post(`${getUrl()}/drive/getDrive/${username}`, {password});
export const deleteFile = (username, fileId) => axios.delete(`${getUrl()}/fileSystem/deleteFile/${username}/${fileId}`);
// TODO: fix on backend to avoi pass the full text plain with properties, only pass name and content
export const createFile = (filename, content) => axios.post(`${getUrl()}/TODO`);

export const copyFile = (username, fileId, virtualDirDestination) => axios.post(`${getUrl()}/copyFile/${username}/${fileId}/${virtualDirDestination}`);
export const shareFile = (buddyUserName, ownerUserName, fileId) => axios.post(`${getUrl()}/shareFile/${buddyUserName}/${ownerUserName}/${fileId}`);

export const getSharedFiles = (username) => axios.get(`${getUrl()}/drive/getSharedFiles/${username}`);

export const modifyFileContent = (username, fileId) => axios.post(`${getUrl()}/modifyFileContent/${username}/${fileId}`);
export const moveFile = (username, fileId, newDirId) => axios.post(`${getUrl()}/file/moveFile/${username}/${fileId}/${newDirId}`);

export const downloadFile = (username, fileId) => axios.post(`${getUrl()}/file/downloadFile/${username}/${fileId}`);
export const newDirectory =  (username, dirName, dirId) => axios.post(`${getUrl()}/dir/createDirectory/${username}/${dirName}/${dirId}`);

export const newFile = (username, dirId,fileName, fileContent) => axios({
    //localhost:8080/api/file/createFile/madri/f3af1faf-94e5-43db-8466-2d5f62a16dd3
    method: 'post',
    url: `${getUrl()}/file/createFile/${username}/${dirId}`, 
    headers: {},
    data: { 
        'name' : fileName,
        'content': fileContent
    }
    }
    );
export const modifyFile = (username, fileId, fileContent) => axios(
    {
    method: 'post',
    url: `${getUrl()}/file/modifyFileContent/${username}/${fileId}`, 
    headers: {},
    data: {
        'content': fileContent
    }
    }
);
export const createUser = (username, password) => axios(
    {
    method: 'post',
    url: `${getUrl()}/drive/createDrive/${username}`, 
    headers: {},
    data: {
        'password': password
    }
    }
);
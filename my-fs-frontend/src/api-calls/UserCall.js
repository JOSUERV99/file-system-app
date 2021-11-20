import axios from "axios";

import env from "../environment.json";

export const getUrl = () => env.dev.baseUrl;
export const getDrive = (username, password) => axios.post(`${getUrl()}/drive/getDrive/${username}`, {password});
export const deleteFile = (username, fileId) => axios.delete(`${getUrl()}/fileSystem/deleteFile/${username}/${fileId}`);
// TODO: fix on backend to avoi pass the full text plain with properties, only pass name and content
export const createFile = (filename, content) => axios.post(`${getUrl()}/TODO`);

export const copyFile = (username, fileId, virtualDirDestination) => axios.post(`${getUrl()}/copyFile/${username}/${fileId}/${virtualDirDestination}`);
export const shareFile = (buddyUserName, ownerUserName, fileId) => axios.post(`${getUrl()}/shareFile/${buddyUserName}/${ownerUserName}/${fileId}`);

export const getSharedFiles = (username) => axios.post(`${getUrl()}/getSharedFiles/${username}`);

export const modifyFileContent = (username, fileId) => axios.post(`${getUrl()}/modifyFileContent/${username}/${fileId}`);
export const moveFile = (username, fileId, oldDirId, newDirId) => axios.post(`${getUrl()}/moveFile/${username}/${fileId}/${oldDirId}/${newDirId}`);

export const downloadFile = (username, fileId) => axios.post(`${getUrl()}/file/downloadFile/${username}/${fileId}`);
export const newDirectory =  (username, dirName, dirId) => axios.post(`${getUrl()}/dir/createDirectory/${username}/${dirName}/${dirId}`);
import React from "react";
import { Alert } from "react-bootstrap";

const style = {
  position: "fixed",
  top: 0,
  left: 0,
  backgroundColor: "yellow",
  width: "100%",
  height: "6%",
}

const AppBar = (props) => {

    return (
     <div style={style}>
        <h2> U File-System App! </h2>
     </div>
    );
};

export default AppBar;
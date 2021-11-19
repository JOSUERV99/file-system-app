
import { GeistProvider, CssBaseline } from '@geist-ui/react'
import React, { useState } from "react";
import AppLayout from './components/AppLayout';

export const FS_MODE = 0, SIGNIN_MODE = 1, SIGNUP_MODE = 2;
export const EDIT_MODE = 10, EXPLORER_MODE = 11;

const App = () => {

  const [global, setGlobal] = useState({
    drive : null, username : '', driveMode : SIGNIN_MODE, selectedItem : null, password : null, fileMode : EDIT_MODE
  });

  return (
    <div className="App">
        <GeistProvider>
        <CssBaseline />
        <AppLayout global={[global, setGlobal]}/>
        </GeistProvider> 
    </div>
  );
}

export default App;

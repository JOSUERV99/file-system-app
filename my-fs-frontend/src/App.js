
import { GeistProvider, CssBaseline } from '@geist-ui/react'
import AppLayout from './components/AppLayout';

const App = () => {
  return (
    <>
      <GeistProvider>
      <CssBaseline />
     
      <AppLayout />
      </GeistProvider>
    </>
  );
}

export default App;

import {useState} from 'react'
import './App.css'
import EregoldUserContext from "./context/eregold-user-context";
import {Dashboard} from "./modules/dashboard/Dashboard";

function App() {
    const [accounts, setAccounts] = useState([]);

    return (
        <EregoldUserContext.Provider value={{accounts}}>
            <Dashboard/>
        </EregoldUserContext.Provider>
    )
}

export default App

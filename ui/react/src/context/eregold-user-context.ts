import React from "react";
import {AccountModel} from "../models/account.models";

interface EregoldUserContextType {
    accounts: Array<AccountModel>,
    /* TODO fix this, there has to be a better way */
    reloadAccounts: () => void
}

const EregoldUserContext = React.createContext<EregoldUserContextType>(
    {} as EregoldUserContextType
);

export default EregoldUserContext;

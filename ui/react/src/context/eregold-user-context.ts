import React from "react";
import {AccountModel} from "../models/account.models";

interface EregoldUserContextType {
    accounts: Array<AccountModel>
}

const EregoldUserContext = React.createContext<EregoldUserContextType>(
    {} as EregoldUserContextType
);

export default EregoldUserContext;

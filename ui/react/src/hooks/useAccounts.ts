import {useEffect, useState} from "react";
import {AccountModel} from "../models/account.models";
import {CanceledError} from "../services/eregold-api.client";
import {AxiosError} from "axios";
import AccountsService from "../services/accounts.service";

const useAccounts = () => {
    const [accounts, setAccounts] = useState<Array<AccountModel>>([]);
    const [error, setError] = useState("");
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        setIsLoading(true);

        try {
            AccountsService.getAccounts().request.then(resp => {
                setAccounts(resp.data);
            });
        } catch (err) {
            if (err instanceof CanceledError) {
                return;
            }
            setError((err as AxiosError).message);
        } finally {
            setIsLoading(false);
        }
    }, []);

    return {accounts, error, isLoading, setAccounts, setError};
}

export default useAccounts;
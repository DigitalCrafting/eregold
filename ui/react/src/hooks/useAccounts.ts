import {useEffect, useState} from "react";
import {AccountModel} from "../models/account.models";
import {CanceledError} from "../services/eregold-api.client";
import {AxiosError} from "axios";
import {AccountTypeEnum, CurrencyEnum} from "../models/enums";

const useAccounts = () => {
    const [accounts, setAccounts] = useState<Array<AccountModel>>([]);
    const [error, setError] = useState("");
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        setIsLoading(true);

        try {
            setAccounts([
                {
                    accountName: "test",
                    accountNumber: "123123",
                    type: AccountTypeEnum.DEBIT,
                    currency: CurrencyEnum.GLD,
                    currentBalance: 123
                }
            ])

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

export interface AccountModel {
    accountNumber: string;
    accountName: string;
    currentBalance: number;
    currency: CurrencyEnum;
    type: AccountTypeEnum;
}

export type AccountTypeEnum = 'DEBIT' | 'SAVING';
export const AccountTypeEnum = {
    DEBIT: 'DEBIT' as AccountTypeEnum,
    SAVING: 'SAVING' as AccountTypeEnum
};

export type CurrencyEnum = 'GLD';
export const CurrencyEnum = {
    GLD: 'GLD' as CurrencyEnum
};

export interface CreateAccountRequest {
    accountType: AccountTypeEnum;
    currency: CurrencyEnum;
}

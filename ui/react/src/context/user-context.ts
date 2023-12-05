class UserContext {
    private _isLoggedIn: boolean = false;

    set isLoggedIn(value: boolean) {
        this._isLoggedIn = value;
    }

    get isLoggedIn(): boolean {
        return this._isLoggedIn || !!sessionStorage.getItem("token");
    }
}

const userContext = new UserContext();

export default userContext;
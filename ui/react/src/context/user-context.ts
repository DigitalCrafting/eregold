class UserContext {
    private _isLoggedIn: boolean = false;

    set isLoggedIn(value: boolean) {
        this._isLoggedIn = value;
    }

    get isLoggedIn() {
        return this._isLoggedIn || !!sessionStorage.getItem("token");
    }
}

const INSTANCE = new UserContext();

export default INSTANCE;
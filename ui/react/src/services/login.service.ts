import eregoldApiClient from "./eregold-api.client";

class LoginService {
    public login(request: LoginRequest) {
        return eregoldApiClient.post(`/login`, request);
    }
}

export default new LoginService();

export interface LoginRequest {
    userId: string;
    password: [];
}

export interface LoginResponse {
    token: string;
}
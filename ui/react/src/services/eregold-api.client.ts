import axios, {AxiosHeaders, CanceledError} from 'axios';

const axiosClient = axios.create({
    baseURL: "http://localhost:8080",
    withCredentials: true,
    headers: new AxiosHeaders({
        'Content-Type': 'application/json'
    })
});

axiosClient.interceptors.request.use(req => {
    // @ts-ignore
    if (req.url.includes("/login") || req.url.includes("/register")) {
        return req;
    }

    const token = sessionStorage.getItem("token"); // you probably want to store it in localStorage or something
    if (token) {
        req.headers.Authorization = `Bearer ${token}`;
    }
    return req;
});

export default axiosClient;

export {CanceledError};
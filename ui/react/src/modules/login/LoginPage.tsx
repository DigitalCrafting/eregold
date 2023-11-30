import {z} from 'zod';
import {zodResolver} from "@hookform/resolvers/zod";
import {FieldValues, useForm} from "react-hook-form";
import {useNavigate} from "react-router-dom";
import {EregoldCard} from "../common/EregoldCard";

const loginSchema = z.object({
    userId: z.string().min(3),
    password: z.string().min(3)
});

type LoginFormData = z.infer<typeof loginSchema>;

export function LoginPage() {
    const {
        register,
        handleSubmit,
        formState: {errors}
    } = useForm<LoginFormData>({resolver: zodResolver(loginSchema)});
    const navigate = useNavigate();

    const onSubmit = (data: FieldValues) => {

    }

    const onRegisterClicked = () => {
        navigate("/register");
    }

    const onLoginClicked = (data: FieldValues) => {
        navigate("/ui");
    }

    return (
        <EregoldCard>
            <form onSubmit={handleSubmit(onLoginClicked)}>
                <div className="container">
                    <div className="row gy-2">
                        <div className="col-12">
                            <div className="form-group">
                                <label htmlFor="inputUserId">User ID</label>
                                <input
                                    {...register('userId')}
                                    type="text"
                                    className="form-control"
                                    id="inputUserId"
                                    placeholder="Enter your ID"
                                />
                                {errors.userId && <p className="alert alert-danger">{errors.userId.message}</p>}
                            </div>
                        </div>
                        <div className="col-12">
                            <div className="form-group">
                                <label htmlFor="inputPassword">Password</label>
                                <input
                                    {...register('password')}
                                    type="password"
                                    className="form-control"
                                    id="inputPassword"
                                    placeholder="Password"/>
                                {errors.password && <p className="alert alert-danger">{errors.password.message}</p>}
                            </div>
                        </div>
                        <div className="col-3">
                            <button type="button"
                                    className="btn btn-secondary"
                                    onClick={onRegisterClicked}
                            >Register
                            </button>
                        </div>
                        <div className="col-6"></div>
                        <div className="col-3">
                            <button type="submit"
                                    className="btn btn-primary align-self-end"
                                    id="loginButton"
                            >Login
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </EregoldCard>
    );
}
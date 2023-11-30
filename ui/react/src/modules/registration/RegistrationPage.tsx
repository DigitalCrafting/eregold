import {EregoldCard} from "../common/EregoldCard";
import {z} from 'zod';
import {FieldValues, useForm} from "react-hook-form";
import {zodResolver} from "@hookform/resolvers/zod";
import {useNavigate} from "react-router-dom";
import {useEffect} from "react";

const registerSchema = z.object({
    email: z.string().min(1), // I omitted email regex verification for simplicity when using this form.
    password: z.string().min(1),
    passwordCheck: z.string().min(1),
    firstName: z.string().min(1),
    lastName: z.string().min(1),
    passwords: z.unknown()
}).refine(
    (data) => data.password === data.passwordCheck,
    {
        message: "Passwords don't match",
        path: ["passwords"],
    }
);

type RegisterFormData = z.infer<typeof registerSchema>;

export function RegistrationPage() {
    const {
        register,
        handleSubmit,
        formState: {errors}
    } = useForm<RegisterFormData>({resolver: zodResolver(registerSchema)});
    const navigate = useNavigate();

    const onRegisterClicked = (data: FieldValues) => {
        navigate("/login");
    }

    const onCancelClicked = () => {
        navigate("/login");
    }

    return (
        <EregoldCard>
            <form onSubmit={handleSubmit(onRegisterClicked)}>
                <div className="row gy-2">
                    <div className="col-12">
                        <div className="form-group">
                            <label htmlFor="inputEmail">Email address</label>
                            <input
                                {...register('email')}
                                type="text"
                                className="form-control"
                                id="inputEmail"
                                aria-describedby="usernameHelp"
                                placeholder="Enter email"
                            />
                            {errors.email && <p className="alert alert-danger">{errors.email.message}</p>}
                            <small id="usernameHelp" className="form-text text-muted">
                                We'll never share your email with anyone else.
                            </small>
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputFirstName">First name</label>
                            <input
                                {...register('firstName')}
                                type="text"
                                className="form-control"
                                id="inputFirstName"
                                placeholder="Enter first name"
                            />
                            {errors.firstName && <p className="alert alert-danger">{errors.firstName.message}</p>}
                        </div>
                    </div>
                    <div className="col-12">
                        <div className="form-group">
                            <label htmlFor="inputLastName">Last name</label>
                            <input
                                {...register('lastName')}
                                type="text"
                                className="form-control"
                                id="inputLastName"
                                placeholder="Enter last name"
                            />
                            {errors.lastName && <p className="alert alert-danger">{errors.lastName.message}</p>}
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
                                placeholder="Enter password"
                            />
                            {errors.password && <p className="alert alert-danger">{errors.password.message}</p>}
                            {errors.passwords && <p className="alert alert-danger">{errors.passwords.message}</p>}
                        </div>
                    </div>
                    <div className="col-12">
                        <div className="form-group">
                            <label htmlFor="inputPasswordCheck">Password</label>
                            <input
                                {...register('passwordCheck')}
                                type="password"
                                className="form-control"
                                id="inputPasswordCheck"
                                placeholder="Re-enter password"/>
                        </div>
                        {errors.passwordCheck && <p className="alert alert-danger">{errors.passwordCheck.message}</p>}
                        {errors.passwords && <p className="alert alert-danger">{errors.passwords.message}</p>}
                    </div>
                    <div className="col-3">
                        <button type="button"
                                id="registerCancelButton"
                                className="btn btn-secondary"
                                onClick={onCancelClicked}
                        >Cancel
                        </button>
                    </div>
                    <div className="col-6"></div>
                    <div className="col-3">
                        <button type="submit"
                                id="registerButton"
                                className="btn btn-primary float-right"
                        >Register
                        </button>
                    </div>
                </div>
            </form>
        </EregoldCard>
    );
}
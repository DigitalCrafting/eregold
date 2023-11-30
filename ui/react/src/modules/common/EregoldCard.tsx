import {ReactNode} from "react";

interface Props {
    children: ReactNode
}

export function EregoldCard({children}: Props) {
    return (
        <div className="container">
            <div className="row">
                <div className="col-2"></div>
                <div className="col-8">
                    <div className="card">
                        <div className="card-body">
                            {children}
                        </div>
                    </div>
                </div>
                <div className="col-2"></div>
            </div>
        </div>
    );
}
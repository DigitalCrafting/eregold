import {AbstractControl} from "@angular/forms";

export namespace EregoldRegisterValidators {
    export function ValidatePassword(c: AbstractControl): any {
        const p1 = c.get('password');
        const p2 = c.get('passwordCheck');

        if (p2.value && p1.value && p1.value === p2.value) {
            p2.setErrors(null);
        } else if (p2.errors === null) {
            p2.setErrors({
                passwordCheck: {
                    valid: false
                }
            });
        }
        return null;
    }
}
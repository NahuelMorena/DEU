import { ForbiddenError } from "@/store/errors/forbiddenError";
import { SessionExpired } from "@/store/errors/sessionExpired";
// import { Unexpected } from "@/store/errors/unexpected";
import { closeLoading } from "./functions";

export function ErrorManagment(error) {
    // if (error.code == "ERR_NETWORK") {
    //     let unexpectedStore = Unexpected();
    //     unexpectedStore.open();
    //     closeLoading();
    // }
    if (!error.response) throw error;
    switch (error.response.status) {
        case 401: {
            let sessionExpired = SessionExpired();
            sessionExpired.open();
            closeLoading();
            break;
        }
        case 403: {
            let forbiddenError = ForbiddenError();
            forbiddenError.open();
            closeLoading();
            break;
        }
        default:
            closeLoading();
    }
    throw error;
}

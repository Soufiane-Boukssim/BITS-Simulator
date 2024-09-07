export interface GlobalResponse<T> {
    respCode: string;
    respMsg: string;
    result?: T;
}
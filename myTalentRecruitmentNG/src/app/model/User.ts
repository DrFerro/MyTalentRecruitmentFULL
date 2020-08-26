export class User {
    constructor(
        public name: string,
        public lastName1: string,
        public lastName2: string,
        public email: string,
        public phone: string,
        public anonymous: boolean,
        public worker: boolean
    ) {}
}
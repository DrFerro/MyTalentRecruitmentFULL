export class Candidate {
    constructor(
        public name: string,
        public lastName1: string,
        public lastName2: string,
        public email: string,
        public phone: string,
        public fieldId: number,
        public specialityId: number,
        public cityId: number,
        public observations: string,
        //public fileCV: string
        //public fileCV: File

    ) {}
}
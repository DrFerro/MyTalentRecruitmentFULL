import { Candidate } from './Candidate';
import { User } from './User';

export class Suggestion {
    constructor(
        public candidate: Candidate,
        public user: User
    ) {}
}
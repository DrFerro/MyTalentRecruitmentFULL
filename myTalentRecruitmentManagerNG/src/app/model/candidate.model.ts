export interface Candidate {
    id?: number;
    name: string;
    last_name1: string;
    last_name2: string;
    email: string;
    phone: string;

    field: string;
    speciality: string;
    city: string;
    observations: string;
    cv: File;

    date: string;

    customer: string;

/*     viewed: boolean;
    inTest: boolean;
    worker: boolean;
    paid: boolean; */

    stateCandidate: number;

  }
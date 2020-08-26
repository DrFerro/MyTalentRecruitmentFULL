import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import { SuggestionComponent } from './components/suggestion/suggestion.component';

const APP_ROUTES: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'suggestion', component: SuggestionComponent },
    { path: '**', pathMatch: 'full', redirectTo: 'home' }
];

export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES, {useHash:true});
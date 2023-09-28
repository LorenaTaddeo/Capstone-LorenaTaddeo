import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { BookComponent } from './pages/book/book.component';
import { AuthGuard } from './pages/auth/auth.guard';


const routes: Routes = [
{
  path: '',
  redirectTo: '/home',
  pathMatch: 'full'
},
{
  path: 'home',
  component: HomeComponent,
},
{
  path: 'register',
  component: RegisterComponent,
},
{
  path: 'login',
  component: LoginComponent,
},
{
  path: 'book',
  component: BookComponent, 
  canActivate: [AuthGuard]
}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

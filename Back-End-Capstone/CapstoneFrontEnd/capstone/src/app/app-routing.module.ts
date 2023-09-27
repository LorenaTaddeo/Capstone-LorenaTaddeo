import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { AuthModule } from './pages/auth/auth.module';
import { BookComponent } from './pages/book/book.component';
import { AuthGuard } from './pages/auth/auth.guard';
import { AuthComponent } from './pages/auth/auth.component';


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
  path: 'auth',
  component: AuthComponent,
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
  imports: [RouterModule.forRoot(routes),AuthModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }

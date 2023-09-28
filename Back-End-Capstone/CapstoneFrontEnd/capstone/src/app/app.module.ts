import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './pages/home/home.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './components/header/header.component';
import { HeroComponent } from './components/hero/hero.component';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { OurscootersComponent } from './components/ourscooters/ourscooters.component';
import { DownloadappComponent } from './components/downloadapp/downloadapp.component';
import { WhyscootComponent } from './components/whyscoot/whyscoot.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BookComponent } from './pages/book/book.component';
import { AuthInterceptor } from './pages/auth/auth.interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    HomeComponent,
    HeaderComponent,
    HeroComponent,
    AboutusComponent,
    OurscootersComponent,
    DownloadappComponent,
    WhyscootComponent,
    BookComponent,
    RegisterComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

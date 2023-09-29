import { Component } from '@angular/core';
import { AuthService } from 'src/app/pages/auth/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  isCollapsed= false;
  isLoggedIn$;

constructor(private authService: AuthService){
      this.isLoggedIn$=authService.isLoggedIn$
}

logout(){
  this.authService.logout();
}


}

import { Component, OnInit } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  title = 'Serramenti Artese';
  username = '';
  isDirettore = false;
  isAddettoVendite = false;
  isMagazziniere = false;
  isAddettoProduzione = false;
  isCliente = false;
  isLoggedIn = false;

  constructor(private keycloak: KeycloakService) {}

  async ngOnInit() {
    this.isLoggedIn = this.keycloak.isLoggedIn();
    if (this.isLoggedIn) {
      const parsedToken = this.keycloak.getKeycloakInstance().idTokenParsed || this.keycloak.getKeycloakInstance().tokenParsed;
      if (parsedToken) {
        this.username = (parsedToken['given_name'] || '') + ' ' + (parsedToken['family_name'] || parsedToken['preferred_username'] || '');
      }
      
      const roles = this.keycloak.getUserRoles();
      this.isDirettore = roles.includes('direttore');
      this.isAddettoVendite = roles.includes('addetto_vendite');
      this.isMagazziniere = roles.includes('magazziniere');
      this.isAddettoProduzione = roles.includes('addetto_produzione');
      this.isCliente = roles.includes('cliente');
    }
  }

  login() {
    this.keycloak.login();
  }

  logout() {
    this.keycloak.logout(window.location.origin);
  }
}

import { inject } from '@angular/core';
import { Router, CanActivateFn, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

export const authGuard: CanActivateFn = async (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  const keycloak = inject(KeycloakService);
  const router = inject(Router);

  const authenticated = keycloak.isLoggedIn();

  if (!authenticated) {
    await keycloak.login({
      redirectUri: window.location.origin + state.url,
    });
    return false;
  }

  const requiredRoles = route.data['roles'];
  if (!requiredRoles || requiredRoles.length === 0) {
    return true;
  }

  const userRoles = keycloak.getUserRoles();
  const hasRole = requiredRoles.some((role: string) => userRoles.includes(role));

  if (hasRole) {
    return true;
  } else {
    router.navigate(['/dashboard']);
    return false;
  }
};

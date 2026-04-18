import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class DashboardComponent implements OnInit {
  stats: any = { prodotti: 0, ordini: 0, dipendenti: 0 };

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.http.get<any[]>('http://localhost:8080/api/prodotti').subscribe(
      (res) => { this.stats.prodotti = res.length; this.cdr.detectChanges(); },
      (err) => console.error(err)
    );
    this.http.get<any[]>('http://localhost:8080/api/ordini').subscribe(
      (res) => { this.stats.ordini = res.length; this.cdr.detectChanges(); },
      (err) => console.error(err)
    );
    this.http.get<any[]>('http://localhost:8080/api/dipendenti').subscribe(
      (res) => { this.stats.dipendenti = res.length; this.cdr.detectChanges(); },
      (err) => console.error(err)
    );
  }
}

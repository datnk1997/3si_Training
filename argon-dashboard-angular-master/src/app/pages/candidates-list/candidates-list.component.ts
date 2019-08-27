import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Candidate} from '../../models/candidate';
import {CandidateService} from '../../services/candidate.service';
import {CandidatesDetailComponent} from '../candidates-detail/candidates-detail.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-candidates-list',
  templateUrl: './candidates-list.component.html',
  styleUrls: ['./candidates-list.component.scss']
})
export class CandidatesListComponent implements OnInit {
  candidates: Candidate[];

  constructor(
    private candidateService: CandidateService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.getCandidate();
  }
/*
  private reloadDate() {
    this.candidateService.getCandidateList().subscribe(data => {
      this.candidates = data;
    });
  }
*/
  deleteCandidates(id: number) {
    this.candidateService.deleteCandidate(id)
      .subscribe(
        data => {
          console.log(data);
          this.candidates = data;
          this.ngOnInit();
    }, error => console.log(error));
  }

  candidateDetails(id: number) {
    this.router.navigate(['candidate', id]);
  }

  getCandidate() {
    this.candidateService.getCandidateList().subscribe(data => {
      this.candidates = data;
      console.log(data);
    });
  }
}



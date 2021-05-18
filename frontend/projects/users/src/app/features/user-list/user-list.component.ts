import { SelectionModel } from '@angular/cdk/collections';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { User } from '../../core/model/user';
import { UserService } from '../../core/services/user.service';

@Component({
  selector: 'valid-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss'],
})
export class UserListComponent implements OnInit {
  constructor(private userService: UserService) {}

  public dataSource: any;
  public displayedColumns: string[] = [
    'select',
    'firstName',
    'lastName',
    'processed',
  ];
  selection = new SelectionModel<User>(true, []);

  ngOnInit(): void {
    const initialSelection: any[] = [];
    const allowMultiSelect = true;
    this.getAll();
  }

  getAll(): void {
    this.userService.getUserList().subscribe((response) => {
      this.dataSource = new MatTableDataSource<User>(response);
    });
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  masterToggle() {
    this.isAllSelected()
      ? this.selection.clear()
      : this.dataSource.data.forEach((row: any) => this.selection.select(row));
  }

  checkboxLabel(row?: User): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'}`;
  }

  processRecords() {
    if (!this.selection.isEmpty()) {
      this.userService
        .updateUserList(this.selection.selected)
        .subscribe((updated: User[]) => {
          this.getAll();
        });
    }
  }
}

import {Component, ComponentFactoryResolver, ComponentRef, OnDestroy, ViewChild, ViewContainerRef} from "@angular/core";
import {ObjectsUtils} from "../utils/eregold-utils";

@Component({
  selector: 'dynamic-component-manager',
  template: `<ng-template #container></ng-template>`
})
export class DynamicComponentManagerComponent implements OnDestroy {
  // @ts-ignore
  @ViewChild('container', { read: ViewContainerRef }) container;

  // @ts-ignore
  private _cmpRef: ComponentRef<any>;

  constructor(private resolver: ComponentFactoryResolver) {

  }

  public show(componentType: any): any {
    if (!ObjectsUtils.isNullOrUndefined(this._cmpRef)) {
      this._cmpRef.destroy();
    }

    const fact = this.resolver.resolveComponentFactory(componentType);
    this._cmpRef = this.container.createComponent(fact);

    return this._cmpRef.instance;
  }

  ngOnDestroy() {
    if (!ObjectsUtils.isNullOrUndefined(this._cmpRef)) {
      this._cmpRef.destroy();
    }
  }
}

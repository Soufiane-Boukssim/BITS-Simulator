import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appIpAddressMask]'
})
export class IpAddressMaskDirective {

  constructor(private el: ElementRef) {}

  @HostListener('input', ['$event']) onInputChange(event:any) {
    const input = this.el.nativeElement;
    let value = input.value;

    // Remove all non-digits and limit to 12 digits (maximum for an IP address)
    value = value.replace(/\D/g, '').substring(0, 12);

    // Format as IP address
    const blocks = [];
    for (let i = 0; i < Math.min(4, value.length); i++) {
      blocks.push(value.substring(i * 3, (i + 1) * 3));
    }

    input.value = blocks.join('.');
  }
}

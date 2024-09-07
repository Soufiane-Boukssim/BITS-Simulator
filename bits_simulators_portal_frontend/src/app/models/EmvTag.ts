export class EmvTag {
    tag: string;
    name: string;
    shortName: string;
    format: string;
    length: string;
    source: string;
    description: string;
    template: string;

    constructor(
        tag: string,
        name: string,
        shortName: string,
        format: string,
        length: string,
        source: string,
        description: string,
        template: string
    ) {
        this.tag = tag;
        this.name = name;
        this.shortName = shortName;
        this.format = format;
        this.length = length;
        this.source = source;
        this.description = description;
        this.template = template;
    }
}
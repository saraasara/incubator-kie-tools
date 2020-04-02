import { ComponentType, createElement } from 'react';

import { useForm } from './uniforms';
import AutoField from './AutoField';

export type AutoFieldsProps = {
  autoField?: ComponentType<{ name: string }>;
  element?: ComponentType | string;
  fields?: string[];
  omitFields?: string[];
};

export default function AutoFields({
  autoField,
  element,
  fields,
  omitFields,
  ...props
}: AutoFieldsProps) {
  const { schema } = useForm();

  return createElement(
    element!,
    props,
    (fields ?? schema.getSubfields())
      .filter(field => !omitFields!.includes(field))
      .map(field => createElement(autoField!, { key: field, name: field })),
  );
}

AutoFields.defaultProps = {
  autoField: AutoField,
  element: 'div',
  omitFields: [],
};

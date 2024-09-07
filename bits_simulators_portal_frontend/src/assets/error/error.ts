export const ERROR_MESSAGES:ErrorMessages = {
    "000": {
      en: "Operation successful",
      fr: "Opération réussie"
    },
    "001": {
      en: "Something went wrong",
      fr: "Une erreur s'est produite"
    },
    "404": {
      en: "Does not exist",
      fr: "N'existe pas"
    },
    "400": {
      en: "Form not valid",
      fr: "La form n'est pas valide"
    },
    "401": {
      en: "Unauthorized access",
      fr: "Accès non autorisé"
    },
    "409": {
      en: "Already exists",
      fr: "Existe déjà"
    }
  };
  type LanguageCode = 'en' | 'fr' | string;
  interface ErrorMessages {
    [key: string]: {
      [lang in LanguageCode]: string;
    };
  }
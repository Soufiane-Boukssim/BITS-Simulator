export interface NdcDisplayLayout {
  id: {
    profileCode: string;
    screenNumber: string;
  };
  screenName: string;
  screenLanguage: string;
  voiceNumber: string;
  numberConcatenation: number;
  track1Name: boolean;
  lineNumber: number;
  columnNumber: number;
  fgColour: string;
  bgColour: string;
  blinking: string;
  policeNumber: string;
  formFeedFlag: string;
  currentPositionX: string;
  currentPositionY: string;
  currentFgColour: string;
  currentBgColour: string;
  currentBlinking: string;
  currentPolice: string;
}
